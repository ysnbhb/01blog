import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Post } from '../../services/post';
import { PostReq } from '../../../model/Post.model';
import { marked } from 'marked';
import { MarkdownComponent } from "ngx-markdown";
interface MediaFile {
  id: string;
  file: File;
  preview: string;
  name: string;
  size: number;
  isImage: boolean;
}

@Component({
  selector: 'app-post-form',
  standalone: true,
  imports: [FormsModule, MarkdownComponent],
  templateUrl: './post-form.html',
  styleUrl: './post-form.css',
})
export class PostForm {
  error = '';
  files: MediaFile[] = [];
  dragOver = false;
  isSubmitting = false;
  showPreview = false;
  renderedMarkdown = '';
  content = '';
  constructor(private post: Post) {}

  togglePreview() {
    this.showPreview = !this.showPreview;
  }

  private generateId(): string {
    return Math.random().toString(36).substr(2, 9);
  }

  onFileChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files?.length) this.handleFiles(Array.from(input.files));
  }

  onDragOver(e: DragEvent): void {
    e.preventDefault();
    this.dragOver = true;
  }
  onDrop(e: DragEvent): void {
    e.preventDefault();
    this.dragOver = false;
     (e.dataTransfer);

    if (e.dataTransfer?.files) this.handleFiles(Array.from(e.dataTransfer.files));
  }

  private handleFiles(fileList: File[]): void {
    const valid = fileList.filter(
      (f) => f.type.startsWith('image/') || f.type.startsWith('video/')
    );

    if (valid.length === 0) {
      this.error = 'Only images and videos are allowed.';
      return;
    }

    if (this.files.length + valid.length > 10) {
      this.error = 'Maximum 10 files allowed.';
      return;
    }

    valid.forEach((file) => {
      const reader = new FileReader();
      reader.onload = () => {
        this.files.push({
          id: this.generateId(),
          file,
          preview: reader.result as string,
          name: file.name,
          size: file.size,
          isImage: file.type.startsWith('image/'),
        });
      };
      reader.readAsDataURL(file);
    });
  }

  removeFile(id: string): void {
    this.files = this.files.filter((f) => f.id !== id);
    const el = document.getElementById('fileInput') as HTMLInputElement;
    if (el) el.value = '';
  }

  formatFileSize(bytes: number): string {
    if (bytes === 0) return '0 B';
    const k = 1024;
    const sizes = ['B', 'KB', 'MB', 'GB'];
    const i = Math.floor(Math.log(bytes) / Math.log(k));
    return `${parseFloat((bytes / Math.pow(k, i)).toFixed(2))} ${sizes[i]}`;
  }
  submit(form: NgForm): void {
    if (form.invalid) return;
    this.isSubmitting = true;
    this.error = '';
    const data = new FormData();
    Object.entries(form.value).forEach(([k, v]) => data.append(k, v as string));
    this.files.forEach((f) => data.append('photo', f.file));
    this.post.create_post(data).subscribe({
      next: (_: PostReq) => {
        this.isSubmitting = false;
        form.reset();
        this.files = [];
      },
      error: (err) => {
        this.isSubmitting = false;
         (err);

        this.error = err.error.error || 'Something went wrong';
      },
    });
  }
}
