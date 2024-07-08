const API_URL = process.env.API_URL || '/api';

export enum Method {
  GET = 'GET',
  POST = 'POST',
  PUT = 'PUT',
}

export interface ApiQueryParams {
  [key: string]: string | number | boolean;
}

export function buildQueryString(params: ApiQueryParams) {
  const query = Object.entries(params)
    .filter(([, value]) => value !== undefined)
    .map(([key, value]) => [key, encodeURIComponent(String(value))]);

  return `?${new URLSearchParams(Object.fromEntries(query)).toString()}`;
}

export async function apiRequest<T>(
  endpoint: string,
  query: ApiQueryParams = {},
  method: Method,
  body?: any,
  hasBody: boolean = true
): Promise<T> {
  const queryString: string = buildQueryString({ ...query });
  try {
    const url = `${API_URL}/${endpoint}${queryString}`;
    const requestOptions: RequestInit = {
      method: method.valueOf(),
    };

    if (body) {
      requestOptions.body = JSON.stringify(body);
      requestOptions.headers = {
        'Content-Type': 'application/json',
      };
    }
    const response = await fetch(url, requestOptions);
    if (!response.ok) {
      throw new Error(`API request failed: ${response.statusText}`);
    }
    return hasBody ? response.json() : null;
  } catch (error) {
    throw error;
  }
}
