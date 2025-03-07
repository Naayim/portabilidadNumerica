import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module';
import { enableProdMode } from '@angular/core';
import { provideAnimations } from '@angular/platform-browser/animations';
import { environment } from './app/environment/environment';

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic().bootstrapModule(AppModule, {
  providers: [provideAnimations()],
  ngZoneEventCoalescing: true,
})
  .catch(err => console.error(err));
