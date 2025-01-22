import { Routes } from '@angular/router';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { SquadronViewComponent } from './components/squadron-view/squadron-view.component';
import { PersonnelViewComponent } from './components/personnel-view/personnel-view.component';
import { ShipsViewComponent } from './components/ships-view/ships-view.component';

export const routes: Routes = [
  {
    path: 'welcome',
    component: LandingPageComponent,
  },
  {
    path: 'squadronview',
    component: SquadronViewComponent
  },
  {
    path: 'personnelview',
    component: PersonnelViewComponent
  },
  {
    path: 'shipsview',
    component: ShipsViewComponent
  },
  { path: '', redirectTo: '/welcome', pathMatch: 'full' }
];
