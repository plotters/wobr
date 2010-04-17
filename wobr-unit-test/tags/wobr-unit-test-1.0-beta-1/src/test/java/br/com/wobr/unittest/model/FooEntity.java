package br.com.wobr.unittest.model;

public class FooEntity extends _FooEntity
{
	private boolean canBeDeleted = true;

	private boolean canBeSaved = true;

	public void setCanBeDeleted(final boolean canBeDeleted)
	{
		this.canBeDeleted = canBeDeleted;
	}

	public void setCanBeSaved(final boolean canBeSaved)
	{
		this.canBeSaved = canBeSaved;
	}

	@Override
	public void validateForDelete() throws ValidationException
	{
		super.validateForDelete();

		if(!canBeDeleted)
		{
			throw new ValidationException("This foo object can't be deleted");
		}
	}

	@Override
	public void validateForSave() throws ValidationException
	{
		super.validateForSave();

		if(!canBeSaved)
		{
			throw new ValidationException("This foo object can't be saved");
		}
	}
}
