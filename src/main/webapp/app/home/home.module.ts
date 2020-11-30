import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Requerimiento14SharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [Requerimiento14SharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent],
})
export class Requerimiento14HomeModule {}
