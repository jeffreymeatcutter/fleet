import { Routes } from '@angular/router';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { SquadronViewComponent } from './components/squadron-components/squadron-view/squadron-view.component';
import { PersonnelViewComponent } from './components/personnel-components/personnel-view/personnel-view.component';
import { ShipsViewComponent } from './components/ships-components/ships-view/ships-view.component';
import { SquadronAddComponent } from './components/squadron-components/squadron-add/squadron-add.component';
import { PersonnelAddComponent } from './components/personnel-components/personnel-add/personnel-add.component';
import { ShipsAddComponent } from './components/ships-components/ships-add/ships-add.component';

export const routes: Routes = [
  {
    path: 'welcome',
    component: LandingPageComponent,
  },
  {
    path: 'add-squadron',
    component: SquadronAddComponent
  },
  {
    path: 'squadronview',
    component: SquadronViewComponent
  },
  {
    path: 'add-personnel',
    component: PersonnelAddComponent
  },
  {
    path: 'personnelview',
    component: PersonnelViewComponent
  },
  {
    path: 'add-ships',
    component: ShipsAddComponent
  },
  {
    path: 'shipsview',
    component: ShipsViewComponent
  },
  { path: '', redirectTo: '/welcome', pathMatch: 'full' }
];
