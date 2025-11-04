import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserReportView } from './user-report-view';

describe('UserReportView', () => {
  let component: UserReportView;
  let fixture: ComponentFixture<UserReportView>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserReportView]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserReportView);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
