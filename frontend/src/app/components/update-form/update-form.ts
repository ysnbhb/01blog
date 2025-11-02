import { Component, Input } from '@angular/core';
import { image } from '../../../model/images';
import { FormsModule, NgForm } from '@angular/forms';
import { PostReq } from '../../../model/Post.model';
import { Post } from '../../services/post';
import { MediaFile } from '../../../model/MediaFile.model';
import { MarkdownComponent } from 'ngx-markdown';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-form',
  imports: [FormsModule, MarkdownComponent],
  templateUrl: './update-form.html',
  styleUrl: './update-form.css',
})
export class UpdateForm {
  post!: PostReq;
  deletedMedia: string[] = [];
  existingMedia: image[] = [];
  newFiles: MediaFile[] = [];
  dragOver = false;
  showPreview = false;
  renderedMarkdown = '';
  isSubmitting = false;
  error = '';
  constructor(private postSer: Post, private router: Router) {}
  ngOnInit() {
    const urlParams = new URLSearchParams(window.location.search);
    const postId = urlParams.get('postId');
    if (postId) {
      this.postSer.getPost(Number(postId)).subscribe({
        next: (data: PostReq) => {
          this.post = data;
          this.loadExistingMedia();
        },
        error: (error) => {
          console.error('Error fetching post data:', error);
        },
      });
    }
  }

  loadExistingMedia() {
    this.existingMedia = (this.post.images || []).map((img: any) => ({
      id: img.id,
      url: img.url,
      type: img.type,
    }));
  }

  togglePreview() {
    this.showPreview = !this.showPreview;
  }
  onFileChange(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files?.length) this.handleFiles(Array.from(input.files));
  }

  onDragOver(e: DragEvent) {
    e.preventDefault();
    this.dragOver = true;
  }
  onDrop(e: DragEvent) {
    e.preventDefault();
    this.dragOver = false;
    if (e.dataTransfer?.files) this.handleFiles(Array.from(e.dataTransfer.files));
  }

  private handleFiles(files: File[]) {
    const valid = files.filter((f) => f.type.startsWith('image/') || f.type.startsWith('video/'));
    valid.forEach((file) => {
      const reader = new FileReader();
      reader.onload = () => {
        this.newFiles.push({
          id: Math.random().toString(36).substr(2, 9),
          file,
          preview: reader.result as string,
          isImage: file.type.startsWith('image/'),
        });
      };
      reader.readAsDataURL(file);
    });
  }

  removeNewFile(id: string) {
    this.newFiles = this.newFiles.filter((f) => f.id !== id);
  }

  removeExistingMedia(url: string) {
    this.existingMedia = this.existingMedia.filter((m) => m.url !== url);
    this.deletedMedia = this.deletedMedia || [];
    this.deletedMedia.push(url);
  }

  submit(form: NgForm) {
    if (form.invalid) return;

    this.isSubmitting = true;
    this.error = '';

    const data = new FormData();
    data.append('title', this.post.title);
    data.append('content', this.post.content);
    data.append('id', this.post.id as unknown as string);
    this.newFiles.forEach((f) => data.append('photo', f.file));

    if (this.deletedMedia?.length) {
      this.deletedMedia.forEach((id: string) => data.append('deletePhoto', id));
    }

    this.postSer.updatePost(data).subscribe({
      next: () => {
        this.isSubmitting = false;
        this.router.navigate(['/post'], { queryParams: { postId: this.post.id } });
      },
      error: (err) => {
        this.isSubmitting = false;
        this.error = err.error?.error || 'Failed to update post';
      },
    });
  }

  cancel() {
    history.back();
  }
}
