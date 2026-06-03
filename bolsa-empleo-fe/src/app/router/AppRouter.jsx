// bolsa-empleo-fe/src/app/router/AppRouter.jsx
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import { useAuth } from '../../features/auth/context/AuthContext'
import MainLayout from '../../shared/layout/MainLayout'
import ProtectedRoute from './ProtectedRoute'
import AdminRoute from './AdminRoute'

// Public
import HomePage from '../../features/public/pages/HomePage'
import PublicSearchPage from '../../features/public/pages/PublicSearchPage'
import LoginPage from '../../features/auth/pages/LoginPage'
import RegistroEmpresaPage from '../../features/registro/pages/RegistroEmpresaPage'
import RegistroOferentePage from '../../features/registro/pages/RegistroOferentePage'
import AccessDeniedPage from '../../features/auth/pages/AccessDeniedPage'
import SessionExpiredPage from '../../features/auth/pages/SessionExpiredPage'

// Empresa
import EmpresaDashboardPage from '../../features/empresa/pages/EmpresaDashboardPage'
import PuestoListPage from '../../features/empresa/pages/PuestoListPage'
import PuestoFormPage from '../../features/empresa/pages/PuestoFormPage'
import BuscarCandidatosPage from '../../features/empresa/pages/BuscarCandidatosPage'

// Oferente
import OferenteDashboardPage from '../../features/oferente/pages/OferenteDashboardPage'
import PerfilPage from '../../features/oferente/pages/PerfilPage'
import HabilidadesPage from '../../features/oferente/pages/HabilidadesPage'
import SubirCvPage from '../../features/oferente/pages/SubirCvPage'

// Admin
import AdminDashboardPage from '../../features/admin/pages/AdminDashboardPage'
import EmpresasPendientesPage from '../../features/admin/pages/EmpresasPendientesPage'
import OferentesPendientesPage from '../../features/admin/pages/OferentesPendientesPage'
import CaracteristicasPage from '../../features/admin/pages/CaracteristicasPage'

export default function AppRouter() {
    const { cargando } = useAuth()
    if (cargando) return <div>Cargando...</div>

    return (
        <BrowserRouter>
            <Routes>
                <Route element={<MainLayout />}>
                    {/* Rutas públicas */}
                    <Route path="/"               element={<HomePage />} />
                    <Route path="/buscar"         element={<PublicSearchPage />} />
                    <Route path="/login"          element={<LoginPage />} />
                    <Route path="/registro/empresa"  element={<RegistroEmpresaPage />} />
                    <Route path="/registro/oferente" element={<RegistroOferentePage />} />
                    <Route path="/acceso-denegado"   element={<AccessDeniedPage />} />
                    <Route path="/sesion-expirada"   element={<SessionExpiredPage />} />

                    {/* Empresa */}
                    <Route element={<ProtectedRoute rol="EMPRESA" />}>
                        <Route path="/empresa"                element={<EmpresaDashboardPage />} />
                        <Route path="/empresa/puestos"        element={<PuestoListPage />} />
                        <Route path="/empresa/puestos/nuevo"  element={<PuestoFormPage />} />
                        <Route path="/empresa/puestos/:id"    element={<PuestoFormPage />} />
                        <Route path="/empresa/candidatos"     element={<BuscarCandidatosPage />} />
                    </Route>

                    {/* Oferente */}
                    <Route element={<ProtectedRoute rol="OFERENTE" />}>
                        <Route path="/oferente"             element={<OferenteDashboardPage />} />
                        <Route path="/oferente/perfil"      element={<PerfilPage />} />
                        <Route path="/oferente/habilidades" element={<HabilidadesPage />} />
                        <Route path="/oferente/cv"          element={<SubirCvPage />} />
                    </Route>

                    {/* Admin */}
                    <Route element={<AdminRoute />}>
                        <Route path="/admin"                    element={<AdminDashboardPage />} />
                        <Route path="/admin/empresas"           element={<EmpresasPendientesPage />} />
                        <Route path="/admin/oferentes"          element={<OferentesPendientesPage />} />
                        <Route path="/admin/caracteristicas"    element={<CaracteristicasPage />} />
                    </Route>

                    <Route path="*" element={<Navigate to="/" replace />} />
                </Route>
            </Routes>
        </BrowserRouter>
    )
}