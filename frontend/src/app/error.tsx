'use client';

import { useEffect } from 'react'
import style from "./error.module.scss";

interface Error {
    error: Error & { digest?: string }
}

export default function Error({ error }: Error) {
    useEffect(() => console.error(error), [error])
    return <h2 className={style.error}>Something went wrong!</h2>;
}