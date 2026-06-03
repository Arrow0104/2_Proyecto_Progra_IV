// bolsa-empleo-fe/src/features/empresa/pages/PuestoListPage.jsx
import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { empresaService } from '../services/empresaService'
import PuestoCard from '../components/PuestoCard'
import Loading from '../../../shared/components/Loading'
import Alert from '../../../shared/components/Alert'

export default function PuestoListPage() {
    const [puestos, setPuestos] = useState([])
    const [cargando, setCargando] = useState(true)
    const [mensaje, setMensaje] = useState({ tipo: '', texto: '' })

    function cargar() {
        setCargando(true)
        empresaService.getMisPuestos()
            .then(setPuestos)
            .catch(e => setMensaje({ tipo: 'error', texto: e.mensaje || 'Error' }))
            .finally(() => setCargando(false))
    }

    useEffect(() => { cargar() }, [])

    async function eliminar(id) {
        if (!confirm('¿Eliminar este puesto?')) return
        try {
            await empresaService.deletePuesto(id)
            setMensaje({ tipo: 'success', texto: 'Puesto eliminado' })
            cargar()
        } catch (e) {
            setMensaje({ tipo: 'error', texto: e.mensaje || 'Error al eliminar' })
        }
    }

    if (cargando) return <Loading />

    return (
        <div>
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                <h2>Mis puestos</h2>
                <Link to="/empresa/puestos/nuevo" style={btnStyle}>+ Nuevo puesto</Link>
            </div>
            <Alert tipo={mensaje.tipo} mensaje={mensaje.texto} />
            {puestos.length === 0
                ? <p>No tenés puestos publicados aún.</p>
                : puestos.map(p => <PuestoCard key={p.idPuesto} puesto={p} onEliminar={eliminar} />)
            }
        </div>
    )
}

const btnStyle = { padding: '0.5rem 1rem', background: '#1e3a5f', color: 'white', borderRadius: '4px', textDecoration: 'none' }