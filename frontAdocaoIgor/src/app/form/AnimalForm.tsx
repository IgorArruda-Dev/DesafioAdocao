'use client';
import React, { useState } from 'react';
import { useRouter } from 'next/navigation';
import { Animal, AnimalType, Status } from '@/app/types/animal';
import { AnimalImage } from '@/app/components/AnimalImage';
import { CreateAnimal } from '@/app/service/AnimalService';
import { AlertBox } from '@/app/components/AlertBox';
import { Spinner } from '@/app/components/Spinner';

function AnimalForm() {
  const router = useRouter();
  const [urlimage, setUrlImage] = useState('');
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [category, setCategory] = useState('CAT');
  const [birthdate, setBirthDate] = useState('');
  const [error, setError] = useState('');

  const showError = (message: string) => {
    setError(message);
    setTimeout(() => {
      setError('');
    }, 1000 * 3);
  };

  const submitForm = async () => {
    if (birthdate === '') {
      showError('Birthdate is required');
      return;
    }

    let animal: Animal = {
      id: 0,
      urlimage: urlimage,
      name: name,
      description: description,
      category: AnimalType[category as keyof typeof AnimalType],
      birthdate: new Date(birthdate),
      status: Status.AVAILABLE,
    };
    await CreateAnimal(animal)
      .then(() => cancelForm())
      .catch((err) => {
        console.log(err);
        showError('Submit failed. Try again!');
      });
  };

  const cancelForm = () => {
    router.push('/');
  };

  return (
    <div>
      {error != '' && <AlertBox message={error} />}
      <div className='flex justify-center'>
        <div className='mt-24 flex w-full max-w-md flex-col items-center rounded-xl border p-4'>
          <div className='mb-4 h-48 w-48 overflow-hidden rounded-full'>
            <AnimalImage
              urlImage={urlimage === '' ? './paw.png' : urlimage}
              name={name}
            />
          </div>
          <form
            className='w-full'
            onSubmit={(e) => {
              e.preventDefault();
              submitForm();
            }}
          >
            <div className='flex flex-col gap-4'>
              <input
                className='w-full rounded border border-gray-300 p-2 text-black'
                type='text'
                placeholder='URL image'
                value={urlimage}
                onChange={(e) => setUrlImage(e.target.value)}
              />
              <input
                className='w-full rounded border border-gray-300 p-2 text-black'
                type='text'
                placeholder='Name'
                value={name}
                maxLength={100}
                onChange={(e) => setName(e.target.value)}
              />
              <textarea
                className='w-full rounded border border-gray-300 p-2 text-black'
                placeholder='Description'
                value={description}
                maxLength={255}
                onChange={(e) => setDescription(e.target.value)}
              />
              <select
                className='w-full rounded border border-gray-300 p-2 text-center text-black'
                value={category}
                onChange={(e) => setCategory(e.target.value)}
              >
                {Object.entries(AnimalType).map(([key, value]) => (
                  <option key={key} value={value}>
                    {value}
                  </option>
                ))}
              </select>
              <input
                className='w-full rounded border border-gray-300 p-2 text-center text-black'
                type='date'
                placeholder='Birthdate'
                value={birthdate}
                onChange={(e) => setBirthDate(e.target.value)}
              />
              <div className='flex flex-row justify-center gap-2'>
                <button
                  className='rounded-md bg-green-800 p-2 text-white'
                  type='submit'
                >
                  Submit
                </button>
                <button className='text-white' onClick={cancelForm}>
                  Cancel
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default AnimalForm;
