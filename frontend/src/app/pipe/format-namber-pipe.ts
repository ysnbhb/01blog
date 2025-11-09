import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'formatNamber',
})
export class FormatNamberPipe implements PipeTransform {
  transform(value: number): string {
    if (value < 1000) {
      return `${value}`;
    } else if (value >= 1000 && value < 1000000) {
      return `${(value / 1000).toFixed(2)}K`;
    } else {
      return `${(value / 1000000).toFixed(2)}M`;
    }
  }
}

