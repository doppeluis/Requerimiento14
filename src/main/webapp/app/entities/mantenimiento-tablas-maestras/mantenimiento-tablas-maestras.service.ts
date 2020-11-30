import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMantenimientoTablasMaestras } from 'app/shared/model/mantenimiento-tablas-maestras.model';

type EntityResponseType = HttpResponse<IMantenimientoTablasMaestras>;
type EntityArrayResponseType = HttpResponse<IMantenimientoTablasMaestras[]>;

@Injectable({ providedIn: 'root' })
export class MantenimientoTablasMaestrasService {
  public resourceUrl = SERVER_API_URL + 'api/mantenimiento-tablas-maestras';

  constructor(protected http: HttpClient) {}

  create(mantenimientoTablasMaestras: IMantenimientoTablasMaestras): Observable<EntityResponseType> {
    return this.http.post<IMantenimientoTablasMaestras>(this.resourceUrl, mantenimientoTablasMaestras, { observe: 'response' });
  }

  update(mantenimientoTablasMaestras: IMantenimientoTablasMaestras): Observable<EntityResponseType> {
    return this.http.put<IMantenimientoTablasMaestras>(this.resourceUrl, mantenimientoTablasMaestras, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMantenimientoTablasMaestras>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMantenimientoTablasMaestras[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
