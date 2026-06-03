// bolsa-empleo-fe/src/features/admin/pages/CaracteristicasPage.jsx
import { useEffect, useState } from 'react'
import { adminService } from '../services/adminService'
import CaracteristicaTree from '../components/CaracteristicaTree'
import Loading from '../../../shared/components/Loading'
import Alert from '../../../shared/components/Alert'

export default function CaracteristicasPage() {
    const [cars, setCars] = useState([])
    const [cargando, setCargando] = useState(true)
    const [mensaje, setMensaje] = useState({ tipo: '', texto: '' })
    const [form, setForm] = useState({ nombre: '', padreId: '' })

    function cargar() {
        setCargando(true)
        adminService.getCaracteristicas()
            .then(setCars)
            .catch(() => setMensaje({ tipo: 'error', texto: 'Error cargando' }))
            .finally(() => setCargando(false))
    }

    useEffect(() => { cargar() }, [])

    async function handleAgregar(e) {
        e.preventDefault()
        try {
            await adminService.crearCaracteristica({
                nombre: form.nombre,
                padreId: form.padreId ? Number(form.padreId) : null
            })
            setMensaje({ tipo: 'success', texto: 'Característica agregada' })
            setForm({ nombre: '', padreId: '' })
            cargar()
        } catch {
            setMensaje({ tipo: 'error', texto: 'Error al agregar' })
        }
    }

    async function eliminar(id) {
        if (!confirm('¿Eliminar esta característica?')) return
        try {
            await adminService.eliminarCaracteristica(id)
            setMensaje({ tipo: 'success', texto: 'Eliminada' })
            cargar()
        } catch {
            setMensaje({ tipo: 'error', texto: 'Error al eliminar' })
        }
    }

    if (cargando) return <Loading />

    const raices = cars.filter(c => c.padreId == null)

    return (
        <div>
            <h2>Características</h2>
            <Alert tipo={mensaje.tipo} mensaje={mensaje.texto} />

            <form onSubmit={handleAgregar} style={{ display: 'flex', gap: '0.5rem', marginBottom: '1.5rem', flexWrap: 'wrap' }}>
                <input placeholder="Nombre" value={form.nombre}
                       onChange={e => setForm({ ...form, nombre: e.target.value })}
                       required style={{ padding: '0.5rem', flex: 2 }} />
                <select value={form.padreId} onChange={e => setForm({ ...form, padreId: e.target.value })}
                        style={{ padding: '0.5rem', flex: 2 }}>
                    <option value="">-- Sin padre (categoría raíz) --</option>
                    {raices.map(r => <option key={r.idCaracteristica} value={r.idCaracteristica}>{r.nombre}</option>)}
                </select>
                <button type="submit" style={btnStyle}>Agregar</button>
            </form>

            <CaracteristicaTree caracteristicas={cars} onEliminar={eliminar} />
        </div>
    )
}

const btnStyle = { padding: '0.5rem 1rem', background: '#1e3a5f', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }