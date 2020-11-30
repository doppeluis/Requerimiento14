import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEditarRegistrarFuente, EditarRegistrarFuente } from 'app/shared/model/editar-registrar-fuente.model';
import { EditarRegistrarFuenteService } from './editar-registrar-fuente.service';
import { EditarRegistrarFuenteComponent } from './editar-registrar-fuente.component';
import { EditarRegistrarFuenteDetailComponent } from './editar-registrar-fuente-detail.component';
import { EditarRegistrarFuenteUpdateComponent } from './editar-registrar-fuente-update.component';

@Injectable({ providedIn: 'root' })
export class EditarRegistrarFuenteResolve implements Resolve<IEditarRegistrarFuente> {
  constructor(private service: EditarRegistrarFuenteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEditarRegistrarFuente> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((editarRegistrarFuente: HttpResponse<EditarRegistrarFuente>) => {
          if (editarRegistrarFuente.body) {
            return of(editarRegistrarFuente.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EditarRegistrarFuente());
  }
}

export const editarRegistrarFuenteRoute: Routes = [
  {
    path: '',
    component: EditarRegistrarFuenteComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'requerimiento14App.editarRegistrarFuente.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EditarRegistrarFuenteDetailComponent,
    resolve: {
      editarRegistrarFuente: EditarRegistrarFuenteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'requerimiento14App.editarRegistrarFuente.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EditarRegistrarFuenteUpdateComponent,
    resolve: {
      editarRegistrarFuente: EditarRegistrarFuenteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'requerimiento14App.editarRegistrarFuente.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EditarRegistrarFuenteUpdateComponent,
    resolve: {
      editarRegistrarFuente: EditarRegistrarFuenteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'requerimiento14App.editarRegistrarFuente.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
