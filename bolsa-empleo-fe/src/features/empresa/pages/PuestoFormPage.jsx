// bolsa-empleo-fe/src/features/empresa/pages/PuestoFormPage.jsx
import { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { empresaService } from '../services/empresaService'
import Alert from '../../../shared/components/Alert'

const VACIO = { titulo: '', descripcion: '', salario: '', estado: 'ACTIVO', tipoPublicacion: 'PUBLICO' }

export default function PuestoFormPage() {
    const { id } = useParams()
    const navigate = useNavigate()
    const [form, setForm] = useState(VACIO)
    const [error, setError] = useState('')
    const [cargando, setCargando] = useState(false)
    const esEdicion = !!id

    useEffect(() => {
        if (!esEdicion) return
        // Cargamos los puestos y buscamos el que coincide
        empresaService.getMisPuestos()
            .then(lista => {
                const p = lista.find(x => String(x.idPuesto) === id)
                if (p) setForm({ titulo: p.titulo, descripcion: p.descripcion,
                    salario: p.salario, estado: p.estado, tipoPublicacion: p.tipoPublicacion })
            })
            .catch(() => setError('No se pudo cargar el puesto'))
    }, [id])

    function handleChange(e) {
        setForm({ ...form, [e.target.name]: e.target.value })
    }

    async function handleSubmit(e) {
        e.preventDefault()
        setError('')
        setCargando(true)
        try {
            const body = { ...form, salario: Number(form.salario) }
            if (esEdicion) await empresaService.updatePuesto(id, body)
            else           await empresaService.createPuesto(body)
            navigate('/empresa/puestos')
        } catch (err) {
            setError(err.mensaje || 'Error al guardar')
        } finally {
            setCargando(false)
        }
    }

    return (
        <div style={{ maxWidth: 600, margin: '0 auto' }}>
            <h2>{esEdicion ? 'Editar puesto' : 'Nuevo puesto'}</h2>
            <Alert tipo="error" mensaje={error} />
            <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '0.75rem' }}>
                <label>Título
                    <input name="titulo" value={form.titulo} onChange={handleChange} required style={inputStyle} />
                </label>
                <label>Descripción
                    <textarea name="descripcion" value={form.descripcion} onChange={handleChange}
                              required rows={4} style={inputStyle} />
                </label>
                <label>Salario (₡)
                    <input name="salario" type="number" value={form.salario} onChange={handleChange} required style={inputStyle} />
                </label>
                <label>Estado
                    <select name="estado" value={form.estado} onChange={handleChange} style={inputStyle}>
                        <option value="ACTIVO">Activo</option>
                        <option value="CERRADO">Cerrado</option>
                    </select>
                </label>
                <label>Tipo de publicación
                    <select name="tipoPublicacion" value={form.tipoPublicacion} onChange={handleChange} style={inputStyle}>
                        <option value="PUBLICO">Público</option>
                        <option value="PRIVADO">Privado</option>
                    </select>
                </label>
                <div style={{ display: 'flex', gap: '1rem' }}>
                    <button type="submit" disabled={cargando} style={btnStyle}>
                        {cargando ? 'Guardando...' : 'Guardar'}
                    </button>
                    <button type="button" onClick={() => navigate('/empresa/puestos')} style={btnSecondary}>
                        Cancelar
                    </button>
                </div>
            </form>
        </div>
    )
}

const inputStyle    = { display: 'block', width: '100%', padding: '0.5rem', marginTop: '0.25rem', boxSizing: 'border-box' }
const btnStyle      = { padding: '0.6rem 1.2rem', background: '#1e3a5f', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }
const btnSecondary  = { padding: '0.6rem 1.2rem', background: '#888', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }