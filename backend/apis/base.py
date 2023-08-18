from apis.version1 import route_products
from apis.version1 import route_login
from apis.version1 import route_users
from apis.version1 import route_query
from fastapi import APIRouter


api_router = APIRouter()
api_router.include_router(route_users.router, prefix="/users", tags=["users"])
api_router.include_router(route_products.router, prefix="/products", tags=["products"])
api_router.include_router(route_login.router, prefix="/login", tags=["login"])
api_router.include_router(route_query.router, prefix="/bot", tags=["bot"])

