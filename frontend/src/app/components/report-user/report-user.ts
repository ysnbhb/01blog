import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { User } from '../../services/user';

@Component({
  selector: 'app-report-user',
  imports: [FormsModule],
  templateUrl: './report-user.html',
  styleUrl: './report-user.css',
})
export class ReportUser {
  @Output() succues = new EventEmitter<string>();
  @Output() error = new EventEmitter<string>();
  @Output() confirmreport = new EventEmitter<boolean>();
  @Input() uuid!: string;
  reportconfirm(confirm: boolean) {
    this.confirmreport.emit(confirm);
  }
  constructor(private userser: User) {}

  onSubmit(form: NgForm) {
    let rep = {
      uuid: this.uuid,
      reason: form.value.reason,
    };

    form.reset();

    this.userser.reportUser(rep).subscribe({
      next: () => {
        this.succues.emit('user reported successfully');
        this.confirmreport.emit(true);
      },
      error: (err) => {
        this.error.emit(err.error.error || 'report user fieled');
        this.confirmreport.emit(true);
      },
    });
  }
}
