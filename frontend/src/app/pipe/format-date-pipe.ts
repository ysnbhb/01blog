import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'formatDate',
})
export class FormatDatePipe implements PipeTransform {
  transform(value: string): string {
    const now = new Date();
    const createdTime = new Date(value);
    const diffInMilliseconds = now.getTime() - createdTime.getTime();
    let diffInHours = Math.floor(diffInMilliseconds / (1000 * 60 * 60));
    if (diffInHours < 1) {
      const diffInMinutes = Math.floor(diffInMilliseconds / (1000 * 60));
      return diffInMinutes < 1 ? 'just now' : diffInMinutes + ' minutes ago';
    }
    if (diffInHours > 24) {
      return Math.floor(diffInMilliseconds / (1000 * 60 * 60 * 24)) + ' days ago';
    }
    return diffInHours + ' hours ago';
  }
}
