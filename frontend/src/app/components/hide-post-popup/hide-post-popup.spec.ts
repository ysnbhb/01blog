import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HidePostPopup } from './hide-post-popup';

describe('HidePostPopup', () => {
  let component: HidePostPopup;
  let fixture: ComponentFixture<HidePostPopup>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HidePostPopup]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HidePostPopup);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
