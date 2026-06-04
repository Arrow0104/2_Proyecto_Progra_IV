// bolsa-empleo-fe/src/shared/layout/MainLayout.jsx
import { Outlet } from 'react-router-dom'
import Navbar from '../components/Navbar'

export default function MainLayout() {
    return (
        <>
            <Navbar />
            <main className="main-content">
                <Outlet />
            </main>
        </>
    )
}