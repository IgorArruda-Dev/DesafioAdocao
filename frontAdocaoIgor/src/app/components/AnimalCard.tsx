'use client';
import React, { useEffect, useState } from 'react';
import { Animal, Status } from '@/app/types/animal';
import { UpdateStatus } from '@/app/service/AnimalService';
import { AnimalImage } from '@/app/components/AnimalImage';
import { AlertBox } from '@/app/components/AlertBox';

function AnimalCard({ animal }: { animal: Animal }) {
  const [status, setStatus] = useState(animal.status);

  const updateStatus = async () => {
    const newStatus =
      status === Status.ADOPTED ? Status.AVAILABLE : Status.ADOPTED;
    await UpdateStatus(animal.id, newStatus)
      .then(() => setStatus(newStatus))
      .catch((err) => console.log(err));
  };

  const birthDate = new Date(animal.birthdate);
  const day = birthDate.getDay();
  const month = birthDate.getMonth();
  const year = birthDate.getFullYear();
  const ad = new Date(Date.now() - birthDate.getTime());
  const age = Math.abs(ad.getUTCFullYear() - 1970);
  return (
    <div>
      <div className='flex h-full min-h-[46vh] w-full flex-col overflow-hidden rounded-lg bg-gray-100 shadow-md'>
        <div className='h-1/2 w-full'>
          <AnimalImage urlImage={animal.urlimage} name={animal.name} />
        </div>
        <div className='flex h-1/2 w-full flex-col gap-4 px-4'>
          <div className='grow'>
            <h2 className='text-xl font-semibold text-gray-800'>
              {animal.name}
            </h2>
            <p className='text-justify text-gray-600'>
              {animal.description.length > 155
                ? `${animal.description.slice(0, 114)}...`
                : animal.description}
            </p>
          </div>
          <div className='grid grid-cols-2 gap-2 pb-4 text-sm text-gray-700'>
            <div className='flex flex-col'>
              <p>
                <strong>Categoria:</strong>
              </p>
              <span> {animal.category === 'CAT' ? 'GATO' : 'CACHORRO'} </span>
            </div>
            <div className='flex flex-col'>
              <p>
                <strong>Data de Nascimento:</strong>
              </p>
              <p>
                {day}/{month}/{year}
              </p>
            </div>
            <div className='flex flex-col'>
              <p>
                <strong>Idade</strong>
              </p>
              <p>
                {age < 1
                  ? 'menos de 12 meses'
                  : `${age.toString()} ${age > 1 ? 'anos' : 'ano'}`}
              </p>
            </div>
            <div className='flex flex-col'>
              <p>
                <strong>Status:</strong>
              </p>
              <label className='flex items-center'>
                <input
                  className='box-border cursor-pointer rounded-md accent-green-600 shadow-md'
                  type='checkbox'
                  checked={status === 'ADOPTED'}
                  onChange={updateStatus}
                />
                <span className='ml-2'>
                  {status === 'ADOPTED' ? 'Adotado' : 'Disponível'}
                </span>
              </label>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AnimalCard;
