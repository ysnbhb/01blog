import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotifView } from './notif-view';

describe('NotifView', () => {
  let component: NotifView;
  let fixture: ComponentFixture<NotifView>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NotifView]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NotifView);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
