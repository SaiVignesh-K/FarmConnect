from typing import List
from typing import Optional

from apis.version1.route_login import get_current_user_from_token
from db.models.users import User
from db.repository.products import create_new_job
from db.repository.products import delete_job_by_id
from db.repository.products import list_products
from db.repository.products import retreive_job
from db.repository.products import search_job
from db.repository.products import update_job_by_id
from db.session import get_db
from fastapi import APIRouter
from fastapi import Depends
from fastapi import HTTPException
from fastapi import status
from fastapi.templating import Jinja2Templates
from schemas.products import JobCreate
from schemas.products import ShowJob
from sqlalchemy.orm import Session


router = APIRouter()
templates = Jinja2Templates(directory="templates")


@router.post("/create-product/", response_model=ShowJob)
def create_product(
    job: JobCreate,
    db: Session = Depends(get_db),
    current_user: User = Depends(get_current_user_from_token),
):
    job = create_new_job(job=job, db=db, owner_id=current_user.id)
    return job


@router.get(
    "/get/{id}", response_model=ShowJob
)  # if we keep just "{id}" . it would stat catching all routes
def read_product(id: int, db: Session = Depends(get_db)):
    job = retreive_job(id=id, db=db)
    if not job:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail=f"Job with this id {id} does not exist",
        )
    return job


@router.get("/all", response_model=List[ShowJob])
def read_products(db: Session = Depends(get_db)):
    products = list_products(db=db)
    return products


@router.put("/update/{id}")
def update_products(id: int, job: JobCreate, db: Session = Depends(get_db)):
    current_user = 1
    message = update_job_by_id(id=id, job=job, db=db, owner_id=current_user)
    if not message:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND, detail=f"Job with id {id} not found"
        )
    return {"msg": "Successfully updated data."}


@router.delete("/delete/{id}")
def delete_products(
    id: int,
    db: Session = Depends(get_db),
    current_user: User = Depends(get_current_user_from_token),
):
    job = retreive_job(id=id, db=db)
    if not job:
        return HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail=f"Job with id {id} does not exist",
        )
    print(job.owner_id, current_user.id, current_user.is_superuser)
    if job.owner_id == current_user.id or current_user.is_superuser:
        delete_job_by_id(id=id, db=db, owner_id=current_user.id)
        return {"detail": "Successfully deleted."}
    raise HTTPException(
        status_code=status.HTTP_401_UNAUTHORIZED, detail="You are not permitted!!!!"
    )


@router.get("/search/{term}")
def autocomplete(term: Optional[str] = None, db: Session = Depends(get_db)):
    products = search_job(term, db=db)
    job_titles = []
    for job in products:
        job_titles.append(job.product_name)
    return job_titles
