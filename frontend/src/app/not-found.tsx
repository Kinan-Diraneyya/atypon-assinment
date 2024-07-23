'use client';
import style from "./not-found.module.scss";

export default function NotFound() {
    return (
        <div className={style.error}>
            <div className={style.content}>
                <h2 className={style.title}>404</h2>
                <div className={style.message}>This page could not be found.</div>
            </div>
        </div>
    );
}