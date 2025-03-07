import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { auGuard } from './auth.guard';

describe('auGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => auGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
