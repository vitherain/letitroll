import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { FeaturesComponent } from './components/features/features.component';
import { FeaturesListComponent } from './components/features-list/features-list.component';

const featuresRoutes: Routes = [
  {
    path: '',
    component: FeaturesComponent,
    children: [
      {
        path: '',
        component: FeaturesListComponent
      },
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(featuresRoutes)
  ],
  exports: [RouterModule]
})
export class FeaturesRoutingModule { }
