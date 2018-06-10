import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { FeaturesComponent } from './components/features/features.component';
import { FeaturesListComponent } from './components/features-list/features-list.component';
import { UnselectedProjectComponent } from './components/unselected-project/unselected-project.component';
import { TargetingComponent } from './components/targeting/targeting.component';

const featuresRoutes: Routes = [
  {
    path: '',
    component: FeaturesComponent,
    children: [
      {
        path: '',
        component: UnselectedProjectComponent
      },
      {
        path: ':projectName/:environmentName',
        children: [
          {
            path: '',
            component: FeaturesListComponent
          },
          {
            path: 'feature-targetings/:targetingId',
            component: TargetingComponent
          }
        ]
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(featuresRoutes)],
  exports: [RouterModule]
})
export class FeaturesRoutingModule {}
