import { Animal, Animals, Status } from '@/app/types/animal';
import { apiRequest, Method } from '@/app/service/ApiRequest';
import { Pageable } from '@/app/types/pageable';

export const GetAllAnimals = async (
  page: number,
  limit: number
): Promise<Pageable<Animals>> => {
  return await apiRequest(
    'v1/animals',
    { page: page, perPage: limit },
    Method.GET,
    undefined
  );
};

export const CreateAnimal = async (animal: Animal): Promise<{ id: number }> => {
  return await apiRequest('v1/animals', undefined, Method.POST, animal);
};

export const UpdateStatus = async (
  id: number,
  status: Status
): Promise<null> => {
  return await apiRequest(
    `v1/animals/${id}/${status}`,
    undefined,
    Method.PUT,
    undefined,
    false
  );
};
