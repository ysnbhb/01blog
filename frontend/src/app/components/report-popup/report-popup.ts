import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-report-popup',
  imports: [FormsModule],
  templateUrl: './report-popup.html',
  styleUrl: './report-popup.css',
})
export class ReportPopup {
  @Output() confirmreport = new EventEmitter<boolean>();
  @Output() error = new EventEmitter<string>();
  @Output() succues = new EventEmitter<string>();
  @Input() postId!: number;

  reportconfirm(confirm: boolean) {
    this.confirmreport.emit(confirm);
  }

  async onSubmit(form: NgForm) {
    let rep = {
      postId: this.postId,
      reason: form.value.reason,
    };

    let token = localStorage.getItem('token');
    try {
       (rep);
      let res = await fetch('http://localhost:8080/api/report_post', {
        method: 'POST',
        body: JSON.stringify(rep),
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });

      if (res.ok) {
        this.succues.emit('Post reported successfully.');
        this.confirmreport.emit(false);
      } else {
        let data = await res.json();
        this.confirmreport.emit(false);
        this.error.emit(data.error || 'An error occurred while reporting the post.');
      }
    } catch (e) {
      this.error.emit('Network error. Please try again later.');
      this.confirmreport.emit(false);

      return;
    }
  }
}
