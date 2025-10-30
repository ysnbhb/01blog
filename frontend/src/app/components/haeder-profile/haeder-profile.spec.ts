import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HaederProfile } from './haeder-profile';

describe('HaederProfile', () => {
  let component: HaederProfile;
  let fixture: ComponentFixture<HaederProfile>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HaederProfile]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HaederProfile);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
