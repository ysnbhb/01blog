import { TestBed } from '@angular/core/testing';

import { NotificationServ } from './notifications';

describe('Notifications', () => {
  let service: NotificationServ;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NotificationServ);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
