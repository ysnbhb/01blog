import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccuesShow } from './succues-show';

describe('SuccuesShow', () => {
  let component: SuccuesShow;
  let fixture: ComponentFixture<SuccuesShow>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SuccuesShow]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SuccuesShow);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
