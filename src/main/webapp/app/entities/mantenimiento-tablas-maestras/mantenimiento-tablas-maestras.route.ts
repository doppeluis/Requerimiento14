import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMantenimientoTablasMaestras, MantenimientoTablasMaestras } from 'app/shared/model/mantenimiento-tablas-maestras.model';
import { MantenimientoTablasMaestrasService } from './mantenimiento-tablas-maestras.service';
import { MantenimientoTablasMaestrasComponent } from './mantenimiento-tablas-maestras.component';
import { MantenimientoTablasMaestrasDetailComponent } from './mantenimiento-tablas-maestras-detail.component';
import { MantenimientoTablasMaestrasUpdateComponent } from './mantenimiento-tablas-maestras-update.component';

@Injectable({ providedIn: 'root' })
export class MantenimientoTablasMaestrasResolve implements Resolve<IMantenimientoTablasMaestras> {
  constructor(private service: MantenimientoTablasMaestrasService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMantenimientoTablasMaestras> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((mantenimientoTablasMaestras: HttpResponse<MantenimientoTablasMaestras>) => {
          if (mantenimientoTablasMaestras.body) {
            return of(mantenimientoTablasMaestras.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MantenimientoTablasMaestras());
  }
}

export const mantenimientoTablasMaestrasRoute: Routes = [
  {
    path: '',
    component: MantenimientoTablasMaestrasComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'requerimiento14App.mantenimientoTablasMaestras.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MantenimientoTablasMaestrasDetailComponent,
    resolve: {
      mantenimientoTablasMaestras: MantenimientoTablasMaestrasResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'requerimiento14App.mantenimientoTablasMaestras.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MantenimientoTablasMaestrasUpdateComponent,
    resolve: {
      mantenimientoTablasMaestras: MantenimientoTablasMaestrasResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'requerimiento14App.mantenimientoTablasMaestras.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MantenimientoTablasMaestrasUpdateComponent,
    resolve: {
      mantenimientoTablasMaestras: MantenimientoTablasMaestrasResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'requerimiento14App.mantenimientoTablasMaestras.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
