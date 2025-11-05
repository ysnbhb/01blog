import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostReport } from './post-report';

describe('PostReport', () => {
  let component: PostReport;
  let fixture: ComponentFixture<PostReport>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostReport]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostReport);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
