export type Pageable<T> = {
  currentPage: number;
  perPage: number;
  total: number;
  items: T;
};
