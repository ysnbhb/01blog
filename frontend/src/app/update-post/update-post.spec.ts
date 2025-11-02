import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatePost } from './update-post';

describe('UpdatePost', () => {
  let component: UpdatePost;
  let fixture: ComponentFixture<UpdatePost>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdatePost]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdatePost);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
