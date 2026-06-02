#!/bin/bash
# ============================================================
#  Proyecto 2 — Bolsa de Empleo
#  Crea toda la estructura de carpetas y archivos vacíos
#  Ejecutar desde la carpeta raíz donde viven los dos proyectos
# ============================================================

set -e

echo "=== Creando estructura Backend: JobApplication ==="

BE="JobApplication/src/main/java/cr/ac/una/job"

# --- config ---
mkdir -p "$BE/config"
touch "$BE/config/AppProperties.java"
touch "$BE/config/DataLoader.java"
touch "$BE/config/WebConfig.java"
touch "$BE/config/SecurityConfig.java"

# --- auth ---
mkdir -p "$BE/auth"
touch "$BE/auth/JwtUtil.java"
touch "$BE/auth/JwtAuthFilter.java"
touch "$BE/auth/AuthInterceptor.java"

# --- controllers ---
mkdir -p "$BE/controllers"
touch "$BE/controllers/AuthRestController.java"
touch "$BE/controllers/PublicController.java"
touch "$BE/controllers/EmpresaRestController.java"
touch "$BE/controllers/OferenteRestController.java"
touch "$BE/controllers/PuestoRestController.java"
touch "$BE/controllers/AdminRestController.java"
touch "$BE/controllers/CaracteristicaRestController.java"
touch "$BE/controllers/CvRestController.java"

# --- dtos ---
mkdir -p "$BE/dtos/auth"
touch "$BE/dtos/auth/LoginRequest.java"
touch "$BE/dtos/auth/LoginResponse.java"

mkdir -p "$BE/dtos/empresa"
mkdir -p "$BE/dtos/oferente"
mkdir -p "$BE/dtos/usuario"

mkdir -p "$BE/dtos/puesto"
touch "$BE/dtos/puesto/CreatePuestoRequest.java"
touch "$BE/dtos/puesto/PuestoResponse.java"
touch "$BE/dtos/puesto/UpdatePuestoRequest.java"

mkdir -p "$BE/dtos/caracteristica"
touch "$BE/dtos/caracteristica/CaracteristicaResponse.java"
touch "$BE/dtos/caracteristica/CreateCaracteristicaRequest.java"

# --- models ---
mkdir -p "$BE/models"
touch "$BE/models/Usuario.java"
touch "$BE/models/Empresa.java"
touch "$BE/models/Oferente.java"
touch "$BE/models/Puesto.java"
touch "$BE/models/Caracteristica.java"
touch "$BE/models/OferenteCaracteristica.java"
touch "$BE/models/PuestoCaracteristica.java"
touch "$BE/models/Rol.java"
touch "$BE/models/EstadoUsuario.java"
touch "$BE/models/EstadoPuesto.java"
touch "$BE/models/TipoPublicacion.java"

# --- repositories ---
mkdir -p "$BE/repositories"

# --- services ---
mkdir -p "$BE/services"
touch "$BE/services/EmpresaService.java"
touch "$BE/services/OferenteService.java"
touch "$BE/services/PuestoService.java"
touch "$BE/services/UsuarioService.java"
touch "$BE/services/CaracteristicaService.java"

# --- exceptions ---
mkdir -p "$BE/exceptions"

# --- resources ---
RES="JobApplication/src/main/resources"
mkdir -p "$RES/static"
touch "$RES/application.properties"
touch "$RES/application-dev.properties"

echo "Backend listo."

# ============================================================
echo ""
echo "=== Creando estructura Frontend: bolsa-empleo-fe ==="

FE="bolsa-empleo-fe"

# raíz
mkdir -p "$FE"
touch "$FE/index.html"
touch "$FE/package.json"
touch "$FE/vite.config.js"

SRC="$FE/src"

# --- app ---
mkdir -p "$SRC/app/router"
touch "$SRC/app/App.jsx"
touch "$SRC/app/router/AppRouter.jsx"
touch "$SRC/app/router/ProtectedRoute.jsx"
touch "$SRC/app/router/AdminRoute.jsx"

# --- features/auth ---
mkdir -p "$SRC/features/auth/context"
mkdir -p "$SRC/features/auth/pages"
mkdir -p "$SRC/features/auth/services"
mkdir -p "$SRC/features/auth/utils"
touch "$SRC/features/auth/context/AuthContext.jsx"
touch "$SRC/features/auth/pages/LoginPage.jsx"
touch "$SRC/features/auth/pages/AccessDeniedPage.jsx"
touch "$SRC/features/auth/pages/SessionExpiredPage.jsx"
touch "$SRC/features/auth/services/authService.js"
touch "$SRC/features/auth/utils/jwtUtils.js"
touch "$SRC/features/auth/utils/storage.js"

# --- features/public ---
mkdir -p "$SRC/features/public/pages"
mkdir -p "$SRC/features/public/services"
touch "$SRC/features/public/pages/HomePage.jsx"
touch "$SRC/features/public/pages/PublicSearchPage.jsx"
touch "$SRC/features/public/services/publicService.js"

# --- features/registro ---
mkdir -p "$SRC/features/registro/pages"
mkdir -p "$SRC/features/registro/services"
touch "$SRC/features/registro/pages/RegistroEmpresaPage.jsx"
touch "$SRC/features/registro/pages/RegistroOferentePage.jsx"
touch "$SRC/features/registro/services/registroService.js"

# --- features/empresa ---
mkdir -p "$SRC/features/empresa/pages"
mkdir -p "$SRC/features/empresa/components"
mkdir -p "$SRC/features/empresa/services"
touch "$SRC/features/empresa/pages/EmpresaDashboardPage.jsx"
touch "$SRC/features/empresa/pages/PuestoListPage.jsx"
touch "$SRC/features/empresa/pages/PuestoFormPage.jsx"
touch "$SRC/features/empresa/pages/BuscarCandidatosPage.jsx"
touch "$SRC/features/empresa/components/PuestoCard.jsx"
touch "$SRC/features/empresa/components/CandidatoCard.jsx"
touch "$SRC/features/empresa/services/empresaService.js"

# --- features/oferente ---
mkdir -p "$SRC/features/oferente/pages"
mkdir -p "$SRC/features/oferente/components"
mkdir -p "$SRC/features/oferente/services"
touch "$SRC/features/oferente/pages/OferenteDashboardPage.jsx"
touch "$SRC/features/oferente/pages/PerfilPage.jsx"
touch "$SRC/features/oferente/pages/HabilidadesPage.jsx"
touch "$SRC/features/oferente/pages/SubirCvPage.jsx"
touch "$SRC/features/oferente/components/HabilidadForm.jsx"
touch "$SRC/features/oferente/services/oferenteService.js"

# --- features/admin ---
mkdir -p "$SRC/features/admin/pages"
mkdir -p "$SRC/features/admin/components"
mkdir -p "$SRC/features/admin/services"
touch "$SRC/features/admin/pages/AdminDashboardPage.jsx"
touch "$SRC/features/admin/pages/EmpresasPendientesPage.jsx"
touch "$SRC/features/admin/pages/OferentesPendientesPage.jsx"
touch "$SRC/features/admin/pages/CaracteristicasPage.jsx"
touch "$SRC/features/admin/components/CaracteristicaTree.jsx"
touch "$SRC/features/admin/components/AprobacionCard.jsx"
touch "$SRC/features/admin/services/adminService.js"

# --- shared ---
mkdir -p "$SRC/shared/api"
mkdir -p "$SRC/shared/components"
mkdir -p "$SRC/shared/layout"
mkdir -p "$SRC/shared/styles"
mkdir -p "$SRC/shared/utils"
touch "$SRC/shared/api/apiClient.js"
touch "$SRC/shared/api/apiError.js"
touch "$SRC/shared/components/Alert.jsx"
touch "$SRC/shared/components/FormErrors.jsx"
touch "$SRC/shared/components/Loading.jsx"
touch "$SRC/shared/components/Navbar.jsx"
touch "$SRC/shared/layout/MainLayout.jsx"
touch "$SRC/shared/styles/app.css"
touch "$SRC/shared/utils/formatters.js"

echo "Frontend listo."
echo ""
echo "=== Estructura creada exitosamente ==="
echo "Revisá con: find JobApplication bolsa-empleo-fe -type f | sort"
