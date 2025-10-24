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
  error!: String;
  selectedFile!: File | null;
  @Output() addPost = new EventEmitter<PostReq>();
  onFileChange(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
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
    console.log(data);

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
}
