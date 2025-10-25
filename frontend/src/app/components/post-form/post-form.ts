import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { PostReq } from '../../../model/Post.model';

@Component({
  selector: 'app-post-form',
  imports: [FormsModule],
  templateUrl: './post-form.html',
  styleUrl: './post-form.css',
})
export class PostForm {
  error: string = '';
  selectedFile: File | null = null;
  previewUrl: string | null = null;
  isImage: boolean = false;
  @Output() addPost = new EventEmitter<PostReq>();
  onFileChange(event: Event): void {
    const input = event.target as HTMLInputElement;

    if (input.files && input.files[0]) {
      const file = input.files[0];
      this.selectedFile = file;
      this.isImage = file.type.startsWith('image/');
      const reader = new FileReader();
      reader.onload = (e: ProgressEvent<FileReader>) => {
        this.previewUrl = e.target?.result as string;
      };
      reader.readAsDataURL(file);
    }
  }

  async sumbet(val: NgForm) {
    let data = new FormData();
    Object.entries(val.value).forEach(([key, val]) => {
      data.append(key, val as string);
    });
    if (this.selectedFile) {
      data.append('photo', this.selectedFile);
    }
    let token = localStorage.getItem('token');
    let res = await fetch('http://localhost:8080/api/creat_post', {
      method: 'POST',
      body: data,
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    let post = await res.json();
    if (res.ok) {
      this.addPost.emit(post);
      val.reset();
      this.selectedFile = null;
    } else {
      this.error = post.error;
    }
  }

  removeFile(): void {
    this.selectedFile = null;
    this.previewUrl = null;
    this.isImage = false;
    const fileInput = document.getElementById('image') as HTMLInputElement;
    if (fileInput) {
      fileInput.value = '';
    }
  }
}
