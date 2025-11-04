import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserReport } from './user-report';

describe('UserReport', () => {
  let component: UserReport;
  let fixture: ComponentFixture<UserReport>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserReport]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserReport);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
