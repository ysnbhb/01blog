import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentView } from './comment-view';

describe('CommentView', () => {
  let component: CommentView;
  let fixture: ComponentFixture<CommentView>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CommentView]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CommentView);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
