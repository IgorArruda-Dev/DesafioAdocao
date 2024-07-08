import AnimalList from '@/app/components/AnimalList';
import { RegisterButton } from '@/app/components/RegisterButton';

export default function Home() {
  return (
    <div className='relative min-h-screen'>
      <main className='overflow-y-auto'>
        <div className='container mx-auto pt-4'>
          <h1 className='mb-4 text-center text-xl font-semibold'>
            Animais para Adoção
          </h1>
          <AnimalList />
        </div>
      </main>

      <footer className='fixed bottom-0 left-0 flex w-full justify-end p-4'>
        <div className='sm:p-4 md:px-8 md:py-4'>
          <RegisterButton />
        </div>
      </footer>
    </div>
  );
}
