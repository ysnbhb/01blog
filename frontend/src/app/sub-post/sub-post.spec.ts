import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubPost } from './sub-post';

describe('SubPost', () => {
  let component: SubPost;
  let fixture: ComponentFixture<SubPost>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SubPost]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubPost);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
