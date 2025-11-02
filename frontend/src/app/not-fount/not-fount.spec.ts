import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotFount } from './not-fount';

describe('NotFount', () => {
  let component: NotFount;
  let fixture: ComponentFixture<NotFount>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NotFount]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NotFount);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
