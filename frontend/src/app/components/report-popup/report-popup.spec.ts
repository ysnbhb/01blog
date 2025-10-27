import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportPopup } from './report-popup';

describe('ReportPopup', () => {
  let component: ReportPopup;
  let fixture: ComponentFixture<ReportPopup>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReportPopup]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReportPopup);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
