import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-delete-post-popup',
  imports: [],
  templateUrl: './delete-post-popup.html',
  styleUrl: './delete-post-popup.css',
})
export class DeletePostPopup {
  @Input() id!: number;
  @Output() deleted = new EventEmitter<number>();
  @Output() error = new EventEmitter<string>();
  // errro!: String;

  reset() {
    this.deleted.emit(0);
  }

  async removepost() {
    const token = localStorage.getItem('token');
    try {
      const res = await fetch(`http://localhost:8080/api/delete_post?postId=${this.id}`, {
        method: 'DELETE',
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      if (res.ok) {
        this.deleted.emit(this.id);
      } else {
        let data = await res.json();
        this.error.emit(data.error)
      }
    } catch (err) {
      console.error('Error deleting post:', err);
    }
  }
}
