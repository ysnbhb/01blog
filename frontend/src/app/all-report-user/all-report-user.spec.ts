import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllReportUser } from './all-report-user';

describe('AllReportUser', () => {
  let component: AllReportUser;
  let fixture: ComponentFixture<AllReportUser>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AllReportUser]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllReportUser);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
