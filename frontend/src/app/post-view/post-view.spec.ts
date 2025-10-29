import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostView } from './post-view';

describe('PostView', () => {
  let component: PostView;
  let fixture: ComponentFixture<PostView>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostView]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostView);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
