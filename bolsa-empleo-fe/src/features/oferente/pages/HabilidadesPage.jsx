// bolsa-empleo-fe/src/features/oferente/pages/HabilidadesPage.jsx
import { useEffect, useState } from 'react'
import { api } from '../../../shared/api/apiClient'
import { oferenteService } from '../services/oferenteService'
import HabilidadForm from '../components/HabilidadForm'
import Alert from '../../../shared/components/Alert'
import Loading from '../../../shared/components/Loading'

export default function HabilidadesPage() {
    const [habilidades, setHabilidades] = useState([])
    const [todasCars, setTodasCars] = useState([])
    const [mensaje, setMensaje] = useState({ tipo: '', texto: '' })
    const [cargando, setCargando] = useState(true)

    function cargar() {
        Promise.all([oferenteService.getMisHabilidades(), api.get('/caracteristicas')])
            .then(([h, c]) => { setHabilidades(h); setTodasCars(c) })
            .catch(() => setMensaje({ tipo: 'error', texto: 'Error cargando datos' }))
            .finally(() => setCargando(false))
    }

    useEffect(() => { cargar() }, [])

    async function agregar(body) {
        try {
            await oferenteService.agregarHabilidad(body)
            setMensaje({ tipo: 'success', texto: 'Habilidad agregada' })
            cargar()
        } catch (e) {
            setMensaje({ tipo: 'error', texto: e.mensaje || 'Error al agregar' })
        }
    }

    async function eliminar(idCar) {
        try {
            await oferenteService.eliminarHabilidad(idCar)
            setMensaje({ tipo: 'success', texto: 'Habilidad eliminada' })
            cargar()
        } catch (e) {
            setMensaje({ tipo: 'error', texto: e.mensaje || 'Error al eliminar' })
        }
    }

    // Solo muestra las características que el oferente aún no tiene
    const disponibles = todasCars.filter(c =>
        !habilidades.some(h => h.idCaracteristica === c.idCaracteristica)
    )

    if (cargando) return <Loading />

    return (
        <div>
            <h2>Mis habilidades</h2>
            <Alert tipo={mensaje.tipo} mensaje={mensaje.texto} />
            <HabilidadForm caracteristicas={disponibles} onAgregar={agregar} />
            <table style={tableStyle}>
                <thead>
                <tr><th>Habilidad</th><th>Nivel</th><th></th></tr>
                </thead>
                <tbody>
                {habilidades.length === 0
                    ? <tr><td colSpan={3}>Sin habilidades registradas</td></tr>
                    : habilidades.map(h => (
                        <tr key={h.idCaracteristica}>
                            <td>{h.nombre}</td>
                            <td>{'⭐'.repeat(h.nivel)}</td>
                            <td>
                                <button onClick={() => eliminar(h.idCaracteristica)} style={btnDanger}>
                                    Eliminar
                                </button>
                            </td>
                        </tr>
                    ))
                }
                </tbody>
            </table>
        </div>
    )
}

const tableStyle = { width: '100%', borderCollapse: 'collapse', marginTop: '1rem' }
const btnDanger  = { padding: '0.3rem 0.7rem', background: '#c0392b', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }