'use client';

import { useRouter } from 'next/navigation';
import { PlusCircleIcon } from '@heroicons/react/16/solid';

export function RegisterButton() {
  const router = useRouter();
  const handleClick = () => {
    router.push('/form'); // Navigate to /newpage route
  };
  return (
    <PlusCircleIcon
      width={36}
      height={36}
      color={'green'}
      onClick={handleClick}
    ></PlusCircleIcon>
  );
}
