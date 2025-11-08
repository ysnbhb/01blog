import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'formatNamber',
})
export class FormatNamberPipe implements PipeTransform {
  transform(value: number): string {
    if (value < 1000) {
      return `${value}B`;
    } else if (value >= 1000 && value < 1000000) {
      return `${(value / 1000).toFixed(2)}KB`;
    } else {
      return `${(value / 1000000).toFixed(2)}MB`;
    }
  }
}

