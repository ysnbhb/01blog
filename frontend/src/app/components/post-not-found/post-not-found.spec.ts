import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostNotFound } from './post-not-found';

describe('PostNotFound', () => {
  let component: PostNotFound;
  let fixture: ComponentFixture<PostNotFound>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostNotFound]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostNotFound);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
