import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEditarRegistrarFuente } from 'app/shared/model/editar-registrar-fuente.model';

type EntityResponseType = HttpResponse<IEditarRegistrarFuente>;
type EntityArrayResponseType = HttpResponse<IEditarRegistrarFuente[]>;

@Injectable({ providedIn: 'root' })
export class EditarRegistrarFuenteService {
  public resourceUrl = SERVER_API_URL + 'api/editar-registrar-fuentes';

  constructor(protected http: HttpClient) {}

  create(editarRegistrarFuente: IEditarRegistrarFuente): Observable<EntityResponseType> {
    return this.http.post<IEditarRegistrarFuente>(this.resourceUrl, editarRegistrarFuente, { observe: 'response' });
  }

  update(editarRegistrarFuente: IEditarRegistrarFuente): Observable<EntityResponseType> {
    return this.http.put<IEditarRegistrarFuente>(this.resourceUrl, editarRegistrarFuente, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEditarRegistrarFuente>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEditarRegistrarFuente[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
