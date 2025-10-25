import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ErrorShow } from './error-show';

describe('ErrorShow', () => {
  let component: ErrorShow;
  let fixture: ComponentFixture<ErrorShow>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ErrorShow]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ErrorShow);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
